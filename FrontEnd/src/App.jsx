import './App.css'
import SignUp from './Components/SignUp.jsx'
import Login from './Components/Login.jsx';
import NavBar from './Components/NavBar.jsx';
import Home from './Components/Home.jsx';
import { login, signUp } from './Utils/apiCalls.js';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import BoardController from './Components/BoardController.jsx';

function App() {
  const navigate = useNavigate();

  const [userId, setUserId] = useState(-1686273342);
  const [gameId, setGameId] = useState(0);

  const loginHandler = async (username, password) => {
    const newUserId = await login(username, password);
    if (!newUserId.error && newUserId !== 0) {
      setUserId(newUserId)
    }
    navigate("/");

  }
  const logout = () => {
    setUserId(0);
  }

  const signUpHandler = async (username, password, email) => {
    const newUserId = await signUp(username, password, email);
    if (!newUserId.error) {
      setUserId(newUserId);
    }
    navigate("/");
  }

  return (
    <>
      <NavBar userId={userId} logout={logout} />
      <Routes>
        <Route path="" element={<Home />} />
        <Route path="/play" element={<BoardController userId={userId} />} />
        <Route path="/signUp" element={<SignUp signUpHandler={signUpHandler} />} />
        <Route path="/login" element={<Login loginHandler={loginHandler} />} />
      </Routes>

    </>
  )
}

export default App
