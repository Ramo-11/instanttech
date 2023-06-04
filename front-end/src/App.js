import logo from './logo.svg';
import './App.css';

function App() {

  let headers = {
    "Content-Type": "application/json"
  }

  let body = {
    name: "Omar",
    username: "omarh5877@gmail.com",
    password: "aaaa"
  }

  let fetchContent = {
    headers: headers,
    method: "post",
    body: JSON.stringify(body) 
  }

  console.log(fetchContent)
 
  fetch('http://localhost:8080/api/v1/register', fetchContent)
  .then(response => {
    if (response.ok) {
      // Successful response (status code 2xx)
      return response.json().then(data => {
        // Handle the response data
        console.log('Response:', data);
      });
    } else {
      // Error response (status code is not 2xx)
      return response.json().then(errorData => {
        // Handle the error response
        console.error('Error:', errorData);
      });
    }
  })
  .catch(error => {
    // Handle network or other errors
    console.error('Request failed:', error);
  })

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p> 
          Edit <code>src/App.js</code> and save to reloakd.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
