import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import './index.css'
import '../general.css'


const Registeration = () => {
    const [jwt, setJwt] = useLocalState('', 'jwt')
    let role = ''

    async function register() {
        let name = document.getElementById('name').value
        let username = document.getElementById('username').value
        let password = document.getElementById('password').value
        if (!validateForm(name, username, password)) {
            return
        }

        let headers = {
            'Content-Type': 'application/json'
        }
        
        let body = {
            name:  document.getElementById('name').value,
            username:  document.getElementById('username').value,
            password:  document.getElementById('password').value,
            role: role
        }
        
        let fetchContent = {
            headers: headers,
            method: 'post',
            body: JSON.stringify(body) 
        }
    
        let result = await fetch('http://localhost:8080/api/v1/register', fetchContent)
        let responseResult = await result;
        const responseBody = await result.json()
        
        document.getElementById('submitFlag').innerHTML = responseBody['message']

        if (responseResult.status === 200) {
            document.getElementById('submitFlag').style.color = '#0a3755'
            document.getElementById('submitFlag').style.backgroundColor = '#b7dffa'
            console.log(`Success, body:`)
            console.log(responseBody)
            setJwt(responseBody['token'])
            await sleep(2000)
            window.location.href = '/'
        } else {
            document.getElementById('submitFlag').style.color = '#D8000C'
            document.getElementById('submitFlag').style.backgroundColor = '#FFBABA'
            console.log(`Not Success, body:`)
            console.log(responseBody)
        }
    }

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function validateForm(name, username, password) {
        if (name === '' || username === '' || password === '' || role === '') {
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

    function accountTypeSelection(event) {
        if (event.target.textContent === 'Freelancer') {
            document.getElementById('freelancerButton').style.backgroundColor = '#4157a0'
            document.getElementById('clientButton').style.backgroundColor = '#99a6d2'
        } else {
            document.getElementById('clientButton').style.backgroundColor = '#4157a0'
            document.getElementById('freelancerButton').style.backgroundColor = '#99a6d2'
        }
        role = event.target.textContent
    }

    return (
        <div className='signupForm'>
            <h1>Signup</h1>
            <p className='submitFlag' id='submitFlag'></p>
            <div className='accountType'>
                <h3>Select Account Type</h3>
                <div className='accountTypeButtons' id='accountTypeButtons'>
                    <button id='freelancerButton' type='button' onClick={(event) => accountTypeSelection(event)}>Freelancer</button>
                    <button id='clientButton' type='button' onClick={(event) => accountTypeSelection(event)}>Client</button>
                </div>
            </div>
            <div className='nameField'>
                <input type='text' id='name' placeholder='Full Name' maxLength='50' required={true}></input>
            </div>
            <div className='emailField'>
                <input type='email' id='username' placeholder='Email' maxLength='50' required={true}></input>
            </div>
            <div className='passwordField'>
                <input type='password' id='password' placeholder='Password' maxLength='50' minLength='6' required={true}></input>
            </div>
            <button className='submitSignupFormButton' type='button' onClick={() => register()}>Submit</button>
        </div>
    );
};

export default Registeration;