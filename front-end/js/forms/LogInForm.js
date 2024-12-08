
/*

let bttn = document.getElementById('initBttn');

bttn.addEventListener("click", event => {
    logIn();
});

let logIn = async () => {
    let form = {
        username: document.getElementById('fuser').value,
        password: document.getElementById('fpassword').value
    };

    const response = await fetch('http://localhost:8080/api/v2/login/log', { // Endpoint corrected to match your Spring Boot controller
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(form) // Send form data as JSON
    });

    const usernameIs = document.getElementById("fuser").value;
    const getClientData = await  fetch(`http://localhost:8080/api/v2/clients/findByUsername?username=${usernameIs}`,{
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    if (response.ok) {
        const result = await response.text();
        const clientData = await getClientData.json();
        // Accede al rol dentro de vetUser
        //const role = clientData.data.vetUser.role;

        localStorage.setItem('session', 'active'); // Storing session state in local storage
        localStorage.setItem('clientData', JSON.stringify(clientData.data));
        //console.log(clientData); // Depuración
        //console.log(clientData.data.role); // Depuración



            // Redirect or update UI to indicate successful login
            window.location.replace("http://localhost:63342/vetcare/front-end/html/client/Home.html?_ijt=46okb4i0a7bcrhv3jrka2t612j&_ij_reload=RELOAD_ON_SAVE");





    } else {
        const error = await response.text();
        console.log('Error logging in: ' + error);
    }
};

const checkSession = () => {
    if (localStorage.getItem('session') === 'active') {
        console.log('User is logged in');
        // Perform actions for logged in user
    } else {
        console.log('User is not logged in');
    }
};

// Call checkSession on page load or relevant event
checkSession();

 */

let bttn = document.getElementById('initBttn');

bttn.addEventListener("click", event => {
    logIn();
});

let logIn = async () => {
    let form = {
        username: document.getElementById('fuser').value,
        password: document.getElementById('fpassword').value
    };

    try {
        const response = await fetch('http://localhost:8080/api/v2/login/log', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include', // Important for session handling
            body: JSON.stringify(form)
        });

        if (response.ok) {
            const result = await response.json(); // Parse JSON response

            // Store session and user type
            localStorage.setItem('session', 'active');
            localStorage.setItem('userType', result.userType);

            // Redirect based on user type
            if (result.userType === 'CLIENT') {
                window.location.replace("http://localhost:63342/vetcare/front-end/html/client/Home.html?_ijt=46okb4i0a7bcrhv3jrka2t612j&_ij_reload=RELOAD_ON_SAVE");
            } else if (result.userType === 'EMPLOYEE') {
                // Replace with your employee home page URL
                window.location.replace("http://localhost:63342/vetcare/front-end/html/admin/adminHome.html?_ijt=5nrcja7khjbnkgdgk88h46ll5f&_ij_reload=RELOAD_ON_SAVE");
            } else {
                console.error('Unknown user type');
            }

            // Optionally store user details
            localStorage.setItem('userDetails', JSON.stringify(result.userDetails));

        } else {
            const error = await response.text();
            console.log('Error logging in: ' + error);
            // Handle login error (show message to user, etc.)
        }
    } catch (error) {
        console.error('Login request failed:', error);
    }
};

const checkSession = () => {
    const isLoggedIn = localStorage.getItem('session') === 'active';
    const userType = localStorage.getItem('userType');

    if (isLoggedIn) {
        console.log('User is logged in');
        console.log('User type:', userType);
        // Optional: Additional logged-in user checks
    } else {
        console.log('User is not logged in');
    }
};

// Call checkSession on page load or relevant event
checkSession();