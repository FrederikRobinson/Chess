import axios from 'axios';
import { signUp, login, createGame, makeMove } from '../src/Utils/apiCalls.js';

vi.mock('axios');

const BACKEND_URL = "http://localhost:8080"

// export const createGame = async (userId) => {
//     try {
//         const res = await axios.post("http://localhost:8080/createGame", { userId });
//         return res.data;
//     }
//     catch (e) {
//         return { error: e.response.data }
//     }
// }
const testMove = { startXPos: 0, startYPos: 0, endXPos: 1, endYPos: 1, playerColour: 'W', gameId: 100 };
const exampleResponse = { updatedBoard: [[]], playerTurn: 'B' };
const exampleUserId = 200;
const exampleLoginDetails = { username: "Alice", password: "Password" };
const exampleSignUpDetails = { username: "Alice", password: "Password", email: "email@email.com" };

describe('API tests', () => {
    afterEach(() => {
        vi.clearAllMocks();
        vi.resetAllMocks();
    })
    describe('makeMove tests:', () => {
        afterEach(() => {
            vi.clearAllMocks();
            vi.resetAllMocks();
        })
        it('makeMove should make a call to axios.post with the correct address', async () => {
            //Arrange
            axios.post.mockResolvedValueOnce({ data: exampleResponse });
            //Act
            await makeMove(testMove.startXPos, testMove.startYPos, testMove.endXPos, testMove.endYPos, testMove.playerColour, testMove.gameId);
            //Assert
            expect(axios.post).toHaveBeenCalledWith(`${BACKEND_URL}/makeMove`, testMove)
        });
        it('makeMove should return the expected data on a successful request', async () => {
            axios.post.mockResolvedValueOnce({ data: exampleResponse })
            const result = await makeMove(testMove.startXPos, testMove.startYPos, testMove.endXPos, testMove.endYPos, testMove.playerColour, testMove.gameId);
            expect(result).toEqual(exampleResponse);
        });
        it('getAllPeeps should return the expected error on an unsuccessful request', async () => {
            axios.post.mockRejectedValueOnce({ response: { status: 404, data: "Invalid move" } })
            const result = await makeMove(testMove.startXPos, testMove.startYPos, testMove.endXPos, testMove.endYPos, testMove.playerColour, testMove.gameId);
            expect(result.error).toBe("Invalid move");
        });
    })
    describe('createGame tests:', () => {
        afterEach(() => {
            vi.clearAllMocks();
            vi.resetAllMocks();
        })
        it('createGame should make a call to axios.post with the correct address', async () => {
            //Arrange
            axios.post.mockResolvedValueOnce({ data: exampleResponse });
            //Act
            await createGame(exampleUserId);
            //Assert
            expect(axios.post).toHaveBeenCalledWith(`${BACKEND_URL}/createGame`, { userId: exampleUserId })
        });
        it('createGame should return the expected data on a successful request', async () => {
            axios.post.mockResolvedValueOnce({ data: exampleResponse })
            const result = await createGame(exampleUserId);
            expect(result).toEqual(exampleResponse);
        });
        it('createGame should return the expected error on an unsuccessful request', async () => {
            axios.post.mockRejectedValueOnce({ response: { status: 404, data: "Unable to create game" } })
            const result = await createGame(exampleUserId);
            expect(result.error).toBe("Unable to create game");
        });
    })
    describe('login tests:', () => {
        afterEach(() => {
            vi.clearAllMocks();
            vi.resetAllMocks();
        })
        it('login should make a call to axios.post with the correct address', async () => {
            //Arrange
            axios.post.mockResolvedValueOnce({ data: exampleResponse });
            //Act
            await login(exampleLoginDetails.username, exampleLoginDetails.password);
            //Assert
            expect(axios.post).toHaveBeenCalledWith(`${BACKEND_URL}/login`, exampleLoginDetails)
        });
        it('login should return the expected data on a successful request', async () => {
            axios.post.mockResolvedValueOnce({ data: exampleResponse })
            const result = await login(exampleLoginDetails.username, exampleLoginDetails.password);
            expect(result).toEqual(exampleResponse);
        });
        it('login should return the expected error on an unsuccessful request', async () => {
            axios.post.mockRejectedValueOnce({ response: { status: 404, data: "Unable to login" } })
            const result = await login(exampleLoginDetails.username, exampleLoginDetails.password);
            expect(result.error).toBe("Unable to login");
        });
    })
    describe('signUp tests:', () => {
        afterEach(() => {
            vi.clearAllMocks();
            vi.resetAllMocks();
        })
        it('signUp should make a call to axios.post with the correct address', async () => {
            //Arrange
            axios.post.mockResolvedValueOnce({ data: exampleResponse });
            //Act
            await signUp(exampleSignUpDetails.username, exampleSignUpDetails.password, exampleSignUpDetails.email);
            //Assert
            expect(axios.post).toHaveBeenCalledWith(`${BACKEND_URL}/signUp`, exampleSignUpDetails)
        });
        it('signUp should return the expected data on a successful request', async () => {
            axios.post.mockResolvedValueOnce({ data: exampleResponse })
            const result = await signUp(exampleSignUpDetails.username, exampleSignUpDetails.password, exampleSignUpDetails.email);
            expect(result).toEqual(exampleResponse);
        });
        it('signUp should return the expected error on an unsuccessful request', async () => {
            axios.post.mockRejectedValueOnce({ response: { status: 404, data: "Unable to sign up" } })
            const result = await signUp(exampleSignUpDetails.username, exampleSignUpDetails.password, exampleSignUpDetails.email);
            expect(result.error).toBe("Unable to sign up");
        });
    })
})