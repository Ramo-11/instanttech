// import { useEffect } from 'react';
// import { useLocalState } from './util/LocalStorage';
import { Route, Routes } from "react-router-dom";
import Registeration from "./views/Registeration";
import Home from "./views/Home";
import Login from "./views/Login";
import { PrivateHomeRoute, PrivateLoginAndRegistrationRoute } from "./views/PrivateRoutes";

function App() {
    // Default value of jwt is empty string
    // const [jwt, setJwt] = useLocalState("", "jwt")

    // useEffect(() => {
    //   if (!jwt) {
    //     let headers = {
    //       "Content-Type": "application/json"
    //     }

    //     let body = {
    //       name: "Omar",
    //       username: "user2@gmail.com",
    //       password: "aaaa"
    //     }

    //     let fetchContent = {
    //       headers: headers,
    //       method: "post",
    //       body: JSON.stringify(body)
    //     }

    //     async function register() {
    //       let result = await fetch('http://localhost:8080/api/v1/auth', fetchContent)
    //       let responseResult = await result;
    //       const body = await result.json()

    //       if (responseResult.status === 200) {
    //         console.log('Success')
    //         setJwt(body['token'])
    //       } else {
    //         console.log(`Not Success: ${responseResult.status}`)
    //         console.log(body)
    //       }
    //     }

    //     register()
    //   }

    // }, [])

    return (
        <Routes>
            <Route
                path="/"
                element={
                    <PrivateHomeRoute>
                        <Home />
                    </PrivateHomeRoute>
                }
            />

            <Route
                path="register"
                element={
                    <PrivateLoginAndRegistrationRoute>
                        <Registeration />
                    </PrivateLoginAndRegistrationRoute>
                }
            />
            <Route path="login" element={
				<PrivateLoginAndRegistrationRoute>
					<Login />
				</PrivateLoginAndRegistrationRoute>
			} />
        </Routes>
    );
}

export default App;
