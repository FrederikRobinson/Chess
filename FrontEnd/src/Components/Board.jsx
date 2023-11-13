


const Board = ({ tiles, selectedTile, currentPlayer }) => {



    return (
        <>
            <div className="boardContainer">
                <div className="tilesContainer">
                    {tiles}
                </div>
            </div>
            <div>{selectedTile}{currentPlayer}</div>
        </>
    )
}

export default Board;