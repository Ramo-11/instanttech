import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import './index.css'
import '../general.css'

const Home = () => {
    const [jwt, setJwt] = useLocalState('', 'jwt')

    async function createJob() {
        let headers = {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${jwt}`
        }
        
        let body = {
            title:  document.getElementById('title').value,
            description:  document.getElementById('description').value,
            rate:  document.getElementById('rate').value,
            date:  document.getElementById('date').value,
        }

        let fetchContent = {
            headers: headers,
            method: 'post',
            body: JSON.stringify(body) 
        }

        let result = await fetch('http://localhost:8080/api/v1/jobs', fetchContent)
        let responseResult = await result;
        const responseBody = await result.json()
        
        document.getElementById('submitFlag').innerHTML = responseBody['message']

        if (responseResult.status === 200) {
            document.getElementById("submitFlag").style.color = "#0a3755"
            document.getElementById("submitFlag").style.backgroundColor = "#b7dffa"
            console.log(`Success, body:`)
            console.log(responseBody)
        } else {
            document.getElementById("submitFlag").style.color = "#D8000C"
            document.getElementById("submitFlag").style.backgroundColor = "#FFBABA"
            console.log(`Not Success, body:`)
            console.log(responseBody)
        }
    }
    return (
        // TODO: Hide this view from users of type freelances
        <div className='jobForm'>
            <h1>Create a new job</h1>
            <p className='submitFlag' id='submitFlag'></p>
            <div className='titleField'>
                <input type='text' id='title' placeholder='Title' maxLength='50' required />
            </div>
            <div className='descriptionField'>
                <input type='text' id='description' placeholder='description' maxLength='50' required />
            </div>
            <div className='rateField'>
                <input type='text' id='rate' placeholder='Rate' maxLength='50' required />
            </div>
            <div className='dateField'>
                <input type='date' id='date' maxLength='50' required />
            </div>
            <button className='createJobButton' type='button' onClick={() => createJob()}>Submit</button>
        </div>
    );
};

export default Home;