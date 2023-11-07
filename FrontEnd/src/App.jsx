import './App.css'
import SignUp from './Components/SignUp.jsx'
import Login from './Components/Login.jsx';
import NavBar from './Components/NavBar.jsx';
import Board from './Components/Board.jsx'
import { login, signUp } from './Utils/apiCalls.js';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { useState } from 'react';

function App() {
  const navigate = useNavigate();

  const [userId, setUserId] = useState(0);

  const loginHandler = async (username, password) => {
    try {
      const newUserId = await login(username, password);
      if (newUserId !== 0) {
        setUserId(newUserId)
      }
      navigate("/");
    }
    catch {

    }
  }
  const signUpHandler = async (username, password, email) => {
    try {
      const newUserId = await signUp(username, password, email);
      setUserId(newUserId);
      navigate("/");
    }
    catch {

    }
  }
  return (
    <>
      <NavBar userId={userId} />
      <Routes>
        <Route path="/" element={<Board />} />
        <Route path="signUp" element={<SignUp signUpHandler={signUpHandler} />} />
        <Route path="login" element={<Login loginHandler={loginHandler} />} />
      </Routes>

    </>
  )
}

export default App
