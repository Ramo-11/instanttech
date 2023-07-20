import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import { Navigate } from 'react-router-dom';

// Navigate to the request route (children) if jwt exists and is valid, otherwise, redirect to login page
const PrivateHomeRoute = ({ children }) => {
    const [jwt, setJwt] = useLocalState('', 'jwt')
    return jwt ? children : <Navigate to='/register'/>
};

const PrivateLoginAndRegistrationRoute = ({ children }) => {
    const [jwt] = useLocalState('', 'jwt')
    return jwt ? <Navigate to='/'/> : children
};

export { PrivateHomeRoute, PrivateLoginAndRegistrationRoute }