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
    // return notEmptyBoard;
}
export const signUp = async (username, password, email) => {
    try {
        const res = await axios.post("http://localhost:8080/signUp", { username, password, email });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
    // return notEmptyBoard;
}
export const login = async (username, password) => {
    try {
        const res = await axios.post("http://localhost:8080/login", { username, password });//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
    // return notEmptyBoard;
}
export const createGame = async () => {
    try {
        const res = await axios.get("http://localhost:8080/makeMove");//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        console.log("ThisIsHere");
        console.dir(res);
        return res.data;
    }
    catch (e) {
        console.log("Here");
        console.dir(e);
        return { error: e.response.data }
    }
    // return notEmptyBoard;
}