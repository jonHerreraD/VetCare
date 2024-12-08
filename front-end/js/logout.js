let bttn = document.getElementById('logout');

bttn.addEventListener("click", event => {
    logout();
});

const logout = async () => {
    try {
        const response = await fetch('http://localhost:8080/api/v2/login/logout', {
            method: 'POST',
            credentials: 'include' // Important for session handling
        });

        if (response.ok) {
            // Clear local storage
            localStorage.removeItem('session');
            localStorage.removeItem('userType');
            localStorage.removeItem('userDetails');

            // Redirect to login page
            window.location.replace("http://localhost:63342/vetcare/front-end/html/LogIn.html?_ijt=44pe7l2tdjhil4cfd1b37avvmk&_ij_reload=RELOAD_ON_SAVE");
        } else {
            console.error('Logout failed');
        }
    } catch (error) {
        console.error('Logout request failed:', error);
    }
};