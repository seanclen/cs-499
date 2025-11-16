document.addEventListener('DOMContentLoaded', function() {

    // Show messages if they exist and hide them after a delay
    const messages = document.querySelectorAll('.message');
    messages.forEach(function(message) {
        if (message.textContent.trim().length > 0) {
            message.classList.add('show');
            
            setTimeout(function() {
                message.classList.remove('show');

                setTimeout(function() {
                    message.style.display = 'none'; 
                }, 500); 
            }, 4000); 
        }
    });
});