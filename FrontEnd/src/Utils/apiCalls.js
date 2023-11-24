import axios from "axios"
import { notEmptyBoard } from "./boardUtils"
export const makeMove = async (startXPos, startYPos, endXPos, endYPos, playerColour, gameId) => {
    try {
        const res = await axios.post("http://localhost:8080/makeMove", { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
}
export const signUp = async (username, password, email) => {
    try {
        const res = await axios.post("http://localhost:8080/signUp", { username, password, email });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
}
export const login = async (username, password) => {
    try {
        const res = await axios.post("http://localhost:8080/login", { username, password });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
}
export const createGame = async (userId) => {
    try {
        console.log(userId);
        const res = await axios.post("http://localhost:8080/createGame", { userId });
        console.log("ThisIsHere");
        console.dir(res);
        return res.data;
    }
    catch (e) {
        console.log("Here");
        console.dir(e);
        return { error: e.response.data }
    }
}
export const getGame = async (gameId) => {
    try {
        const res = await axios.post("http://localhost:8080/getGame", { gameId });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        console.log("ThisIsHere");
        console.dir(res);
        return res.data;
    }
    catch (e) {
        console.log("Here");
        console.dir(e);
        return { error: e.response.data }
    }
}
export const joinGame = async (userId, gameId) => {
    try {
        const res = await axios.post("http://localhost:8080/joinGame", { userId, gameId });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        console.log("ThisIsHere");
        console.dir(res);
        return res.data;
    }
    catch (e) {
        console.log("Here");
        console.dir(e);
        return { error: e.response.data }
    }
}