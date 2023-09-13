import Tile from './Tile.jsx'
import { createBoard } from '../Utils/boardUtils.jsx'


const Board = () => {
    const tiles = createBoard();


    return (
        <>
            <p>Board here</p>
            {tiles}
        </>
    )
}

export default Board;