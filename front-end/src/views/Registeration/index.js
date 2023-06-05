import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import './index.css'


const Registeration = () => {
    const [jwt, setJwt] = useLocalState("", "jwt")

    async function register() {
        let name = document.getElementById('name').value
        let username = document.getElementById('username').value
        let password = document.getElementById('password').value
        if (!validateForm(name, username, password)) {
            console.log("Hello?")
            return
        }

        let headers = {
            "Content-Type": "application/json"
        }
        
        let body = {
            name:  document.getElementById("name").value,
            username:  document.getElementById("username").value,
            password:  document.getElementById("password").value
        }

        console.log(body)
        
        let fetchContent = {
            headers: headers,
            method: "post",
            body: JSON.stringify(body) 
        }
    
        let result = await fetch('http://localhost:8080/api/v1/register', fetchContent)
        let responseResult = await result;
        const responseBody = await result.json()
        
        document.getElementById('submitFlag').innerHTML = responseBody['message']

        if (responseResult.status === 200) {
            console.log(`Success, body:`)
            console.log(responseBody)
            setJwt(responseBody['token'])
            await sleep(2000)
            window.location.href = '/'
        } else {
            console.log(`Not success: ${responseBody}`)
        }
    }

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function validateForm(name, username, password) {
        if (name === '' || username === '' || password === '') {
            alert('All fields are required.');
            return false;
        }

        if (!validateEmail(username)) {
            alert('Please enter a valid email address.');
            return false;
        }

        if (password.length < 6) {
            alert('Password should be at least 6 characters long.');
            return false;
        }

        return true
    }

    function validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    return (
        <div>
            <label>Name</label>
            <input type='text' id='name' placeholder='Full Name' maxLength='50' required={true}></input>
            <label>Email</label>
            <input type='email' id='username' placeholder='Email' maxLength='50' required={true}></input>
            <label>Password</label>
            <input type='password' id='password' placeholder='Password' maxLength='50' minLength='6' required={true}></input>
            <button type='button' onClick={() => register()}>Submit</button>
            <p className="submitFlag" id="submitFlag"></p>
        </div>
    );
};

export default Registeration;