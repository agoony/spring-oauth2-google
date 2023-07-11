$(document).ready(function() {
    // Function to handle the form submission
    $("#messageForm").submit(function(event) {
      event.preventDefault(); // Prevent the default form submission
  
      var chatRoomId = $("input[name='chatRoomId']").val();
      var content = $("input[name='content']").val();
  
      // Create a JSON object with the message data
      var messageData = {
        chatRoomId: chatRoomId,
        content: content
      };
  
      // Send an AJAX POST request to the server
      $.ajax({
        type: "POST",
        url: "/chat/sendMessage",
        contentType: "application/json",
        data: JSON.stringify(messageData),
        success: function(response) {
          // Clear the message input field
          $("input[name='content']").val("");
        },
        error: function(error) {
          console.log("Error sending message:", error);
        }
      });
    });
  
    // Function to continuously poll for new messages
    function pollMessages() {
      setInterval(function() {
        var chatRoomId = $("input[name='chatRoomId']").val();
  
        // Send an AJAX GET request to fetch new messages
        $.ajax({
          type: "GET",
          url: "/chat/getMessages?chatRoomId=" + chatRoomId,
          success: function(response) {
            // Update the messages in the UI
            var messages = response.messages;
  
            var messagesList = $("#messages ul");
            messagesList.empty();
  
            for (var i = 0; i < messages.length; i++) {
              var message = messages[i];
              var listItem = $("<li>").text(message.user.username + ": " + message.content);
              messagesList.append(listItem);
            }
          },
          error: function(error) {
            console.log("Error fetching messages:", error);
          }
        });
      }, 2000); // Poll every 2 seconds (adjust as needed)
    }
  
    // Start polling for messages when the page loads
    pollMessages();
  });
  