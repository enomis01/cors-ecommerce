document.addEventListener("DOMContentLoaded", function() {
  // Aggiungi event listener al form di login
  const loginForm = document.getElementById("loginForm");
  loginForm.addEventListener("submit", effettuaLogin);
});

function effettuaLogin(event) {
  event.preventDefault(); // Evita il comportamento predefinito del modulo

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  // Chiamata API per generare un token di autenticazione
  const loginUrl = 'http://127.0.0.1:8080/auth/generate_token';
  const authRequest = {
    email: email,
    password: password,
  };

  fetch(loginUrl, {
    method: 'POST',
    mode: 'no-cors',  // Aggiunta dell'opzione no-cors
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(authRequest),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`Errore nella chiamata API: ${response.status}`);
      }
      return response.json();
    })
    .then(token => {
      // Puoi fare qualcosa con il token se necessario
      mostraNotifica("Login avvenuto con successo!");
    })
    .catch(error => {
      mostraNotifica("Errore durante il login. Controlla le credenziali.");
    });
}

function mostraNotifica(testo) {
  var notifica = document.getElementById("notifica");
  notifica.innerHTML = testo;
  notifica.style.display = "block";

  setTimeout(function () {
    notifica.style.display = "none";
  }, 4000); // Nascondi la notifica dopo 4 secondi
}

function goToRegistration() {
  window.location.href = "registration.html";
}


//____________________________________________________________________________________________________________
//Ecco come puoi adattare il tuo codice per utilizzare XMLHttpRequest:

// document.addEventListener("DOMContentLoaded", function() {
//   // Aggiungi event listener al form di login
//   const loginForm = document.getElementById("loginForm");
//   loginForm.addEventListener("submit", effettuaLogin);
// });

// function effettuaLogin(event) {
//   event.preventDefault(); // Evita il comportamento predefinito del modulo

//   const email = document.getElementById("email").value;
//   const password = document.getElementById("password").value;

//   // Chiamata API per generare un token di autenticazione
//   const loginUrl = 'http://127.0.0.1:8080/auth/generate_token';
//   const authRequest = {
//     email: email,
//     password: password,
//   };

//   const xhr = new XMLHttpRequest();
//   xhr.open('POST', loginUrl, true);
//   xhr.setRequestHeader('Content-Type', 'application/json');

//   xhr.onload = function() {
//     if (xhr.status >= 200 && xhr.status < 300) {
//       const token = JSON.parse(xhr.responseText);
//       mostraNotifica("Login avvenuto con successo!");
//       // Puoi fare qualcosa con il token se necessario
//     } else {
//       mostraNotifica(`Errore nella chiamata API: ${xhr.status}`);
//     }
//   };

//   xhr.onerror = function() {
//     mostraNotifica("Errore durante il login. Controlla le credenziali.");
//   };

//   xhr.send(JSON.stringify(authRequest));
// }

// function mostraNotifica(testo) {
//   var notifica = document.getElementById("notifica");
//   notifica.innerHTML = testo;
//   notifica.style.display = "block";

//   setTimeout(function () {
//     notifica.style.display = "none";
//   }, 4000); // Nascondi la notifica dopo 4 secondi
// }

// function goToRegistration() {
//   window.location.href = "registration.html";
// }

