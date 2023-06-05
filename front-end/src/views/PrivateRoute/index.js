import React from 'react';
import { useLocalState } from '../../util/LocalStorage';
import { Navigate } from 'react-router-dom';

// Navigate to the request route (children) if jwt exists and is valid, otherwise, redirect to login page
const PrivateRoute = ({ children }) => {
    const [jwt, setJwt] = useLocalState("", "jwt")
    return jwt ? children : <Navigate to="/Login"/>
};

export default PrivateRoute;