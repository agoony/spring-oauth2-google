package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private ProductService service;

	@Autowired
	private AdoptionService adoptionService;

	@Autowired
	private ChatService chatService;
	
	
	@RequestMapping("/list")
	public String viewHomePage(Model model) {
		List<Adoption> listAdoptions = adoptionService.listAll();
		model.addAttribute("listAdoptions", listAdoptions);
		
		return "adoptions";
	}

	@RequestMapping("/listProducts")
	public String viewHomePageProducts(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "products";
	}
	
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}

	@RequestMapping("/newAdoption")
	public String showNewAdoptionForm(Model model) {
		Adoption adoption = new Adoption();
		model.addAttribute("adoption", adoption);
		
		return "new_adoption";
	}
	
	
	@RequestMapping("/chatrooms")
	public String showChatrooms(Model model,@RequestParam("adoptionid") String adoptionId) {
		List<ChatRoom> listChatRooms = chatService.getChatRoomByAdoptionId(Long.parseLong(adoptionId));
		model.addAttribute("listChatRooms", listChatRooms);
		return "chatrooms";
	}

	@RequestMapping("/chatroom")
	public String showChatroom(Model model,@RequestParam("chatroomid") String chatroomid) {
		ChatRoom chatRoom = chatService.getChatRoomById(Long.parseLong(chatroomid));
		model.addAttribute("chatRoom", chatRoom);
		return "chatroom";
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/list";
	}

	@RequestMapping(value = "/saveAdoption", method = RequestMethod.POST)
	public String saveAdoption(@ModelAttribute("adoption") Adoption adoption,@RequestParam("adopterusername") String adopterUsername) {
		adoptionService.save(adoption,adopterUsername);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/list";
	}
}
