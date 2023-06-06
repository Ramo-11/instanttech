import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import './index.css'
import '../general.css'

const Login = () => {
    const [jwt, setJwt] = useLocalState('', 'jwt')

    async function loginUser() {
        let headers = {
            'Content-Type': 'application/json',
        }
        
        let body = {
            username:  document.getElementById('username').value,
            password:  document.getElementById('password').value
        }
        
        let fetchContent = {
            headers: headers,
            method: 'post',
            body: JSON.stringify(body) 
        }
    
        let result = await fetch('http://localhost:8080/api/v1/login', fetchContent)
        let responseResult = await result;
        const responseBody = await result.json()
        
        document.getElementById('submitFlag').innerHTML = responseBody['message']

        if (responseResult.status === 200) {
            document.getElementById("submitFlag").style.color = "#0a3755"
            document.getElementById("submitFlag").style.backgroundColor = "#b7dffa"
            console.log(`Success, body:`)
            console.log(responseBody)
            setJwt(responseBody['token'])
            await sleep(2000)
            window.location.href = '/'
        } else {
            document.getElementById("submitFlag").style.color = "#D8000C"
            document.getElementById("submitFlag").style.backgroundColor = "#FFBABA"
            console.log(`Not Success, body:`)
            console.log(responseBody)
        }
        function sleep(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }
    }

    return (
        <div className='loginForm'>
            <h1>Login</h1>
            <p className='submitFlag' id='submitFlag'></p>
            <div className='emailField'>
                <input type='email' id='username' placeholder='Email' maxLength='50' required />
            </div>
            <div className='passwordField'>
                <input type='password' id='password' placeholder='Password' maxLength='50' minLength='6' required />
            </div>
            <button className='submitLoginFormButton' type='button' onClick={() => loginUser()}>Submit</button>
        </div>
    );
};

export default Login;