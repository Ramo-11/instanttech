import React from 'react';
import './index.css'

const Login = () => {
    async function loginUser() {
        let headers = {
            "Content-Type": "application/json"
        }
        
        let body = {
            username:  document.getElementById("username").value,
            password:  document.getElementById("password").value
        }

        console.log(body)
        
        let fetchContent = {
            headers: headers,
            method: "post",
            body: JSON.stringify(body) 
        }
    
        let result = await fetch('http://localhost:8080/api/v1/login', fetchContent)
        let responseResult = await result;
        const responseBody = await result.json()
        
        if (responseResult.status === 200) {
            console.log(`Success: ${responseBody}`)
            console.log(responseBody)
        } else {
            console.log(`Not success: ${responseBody}`)
        }
    }

    return (
        <div>
            <label>Email</label>
            <input type='email' id='username' placeholder='Email' maxLength='50' required />
            <label>Password</label>
            <input type='password' id='password' placeholder='Password' maxLength='50' minLength='6' required />
            <button type='button' onClick={() => loginUser()}>Submit</button>
        </div>
    );
};

export default Login;