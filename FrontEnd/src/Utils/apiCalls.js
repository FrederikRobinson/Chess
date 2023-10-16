import axios from "axios"
import { notEmptyBoard } from "./boardUtils"
export const makeMove = async (startXPos, startYPos, endXPos, endYPos, playerColour, gameId) => {
    try {
        const res = await axios.get("http://localhost:8080/greeting");//, { startXPos, startYPos, endXPos, endYPos, playerColour, gameId });
        return res.data;
    }
    catch (e) {
        return { error: e.response.data }
    }
    return notEmptyBoard;
}

//http://localhost:8080/greeting