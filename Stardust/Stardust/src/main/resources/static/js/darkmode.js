document.addEventListener("DOMContentLoaded", function () {
    let darkmode = localStorage.getItem("darkmode");
    const themeSwitch = document.getElementById("theme_switch");

    const enableDarkmode = () => {
        document.body.classList.add("darkmode");
        localStorage.setItem("darkmode", "enabled");
    };

    const disableDarkmode = () => {
        document.body.classList.remove("darkmode");
        localStorage.setItem("darkmode", "disabled");
    };

    if (darkmode === "enabled") enableDarkmode();

    themeSwitch.addEventListener("click", () => {
        darkmode = localStorage.getItem("darkmode");
        darkmode !== "enabled" ? enableDarkmode() : disableDarkmode();
    });
});
