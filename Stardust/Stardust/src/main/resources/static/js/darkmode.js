document.addEventListener("DOMContentLoaded", function () {
    // ========================
    // Dark Mode Functionality
    // ========================
    let darkmode = localStorage.getItem("darkmode");
    const themeSwitch = document.getElementById("theme_switch");

    // Function to enable dark mode by adding the 'darkmode' class and saving the state
    const enableDarkmode = () => {
        document.body.classList.add("darkmode");
        localStorage.setItem("darkmode", "enabled");
    };

    // Function to disable dark mode by removing the 'darkmode' class and saving the state
    const disableDarkmode = () => {
        document.body.classList.remove("darkmode");
        localStorage.setItem("darkmode", "disabled");
    };

    // Check for dark mode on initial page load
    if (darkmode === "enabled") enableDarkmode();

    // Toggle dark mode when the theme switch button is clicked
    themeSwitch.addEventListener("click", () => {
        darkmode = localStorage.getItem("darkmode");
        darkmode !== "enabled" ? enableDarkmode() : disableDarkmode();
    });

    // =========================
    // Comment Section Functionality
    // =========================

    // Select the necessary elements
    const commentInput = document.getElementById("userComment");
    const publishBtn = document.getElementById("publish");
    const commentsContainer = document.querySelector(".comments");
    const userNameInput = document.querySelector(".user");

    // Enable or disable the Publish button based on whether text is entered
    commentInput.addEventListener("input", () => {
        if (!commentInput.value.trim()) {
            publishBtn.setAttribute("disabled", "disabled");
            publishBtn.classList.remove("abled");
        } else {
            publishBtn.removeAttribute("disabled");
            publishBtn.classList.add("abled");
        }
    });

    // Function to add a new comment post
    function addPost() {
        // Prevent adding an empty comment
        if (!commentInput.value.trim()) return;

        // Create a user object for the comment post
        const userId = {};
        userId.name = userNameInput.value;
        // Check if username is empty or "anonymous" (case-insensitive)
        if (!userId.name || userId.name.toLowerCase() === "anonymous") {
            userId.identity = false;
            userId.image = "anonymous.png"; // Ensure this image exists in your static assets
        } else {
            userId.identity = true;
            userId.image = "user.png"; // Ensure this image exists in your static assets
        }
        userId.message = commentInput.value;
        userId.date = new Date().toLocaleString();

        // Create the comment HTML using a template literal
        const published = `
      <div class="parents">
        <img src="${userId.image}" alt="User Avatar">
        <div>
          <h1>${userId.name}</h1>
          <p>${userId.message}</p>
          <div class="engagements">
            <!-- Like Icon SVG -->
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3">
              <path d="M720-120H280v-520l280-280 50 50q7 7 11.5 19t4.5 23v14l-44 174h258q32 0 56 24t24 56v80q0 7-2 15t-4 15L794-168q-9 20-30 34t-44 14Z"/>
            </svg>
            <!-- Share Icon SVG -->
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3">
              <path d="M680-80q-50 0-85-35t-35-85q0-6 3-28L282-392q-16 15-37 23.5t-45 8.5q-50 0-85-35t-35-85q0-50 35-85t85-35q24 0 45 8.5t37 23.5l281-164q-2-7-2.5-13.5T560-760q0-50 35-85t85-35q50 0 85 35t35 85q0 50-35 85t-85 35q-24 0-45-8.5T598-672L317-508q2 7 2.5 13.5t.5 14.5q0 8-.5 14.5T317-452l281 164q16-15 37-23.5t45-8.5q50 0 85 35t35 85q0 50-35 85t-85 35Z"/>
            </svg>
          </div>
          <span class="date">${userId.date}</span>
        </div>
      </div>
    `;
        // Append the new comment to the comments container
        commentsContainer.insertAdjacentHTML("beforeend", published);

        // Reset the comment input field and disable the publish button
        commentInput.value = "";
        publishBtn.setAttribute("disabled", "disabled");
        publishBtn.classList.remove("abled");
    }

    // Listen for clicks on the Publish button to add the comment
    publishBtn.addEventListener("click", addPost);
});
