import './App.css'
import SignUp from './Components/SignUp.jsx'
import Login from './Components/Login.jsx';
import NavBar from './Components/NavBar.jsx';
import JoinGame from './Components/JoinGame.jsx';
import Board from './Components/Board.jsx'
import { login, signUp } from './Utils/apiCalls.js';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import BoardController from './Components/BoardController.jsx';
import JoinGame from './Components/JoinGame.jsx';

function App() {
  const navigate = useNavigate();

  const [userId, setUserId] = useState(0);
  const [gameId, setGameId] = useState(0);

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
  const logout = () => {
    setUserId(0);
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
  const handleJoinGame = async (gameCode) => {
    try {
      const res = await joinGame(userId, gameCode)
    }
    catch {

    }
  }
  return (
    <>
      <NavBar userId={userId} logout={logout} />
      <Routes>
        <Route path="/" element={<BoardController userId={userId} />} />
        <Route path="joinGame" element={<JoinGame handleJoinGame={handleJoinGame} />} />
        <Route path="signUp" element={<SignUp signUpHandler={signUpHandler} />} />
        <Route path="login" element={<Login loginHandler={loginHandler} />} />
      </Routes>

    </>
  )
}

export default App
