import PropTypes from "prop-types"


const Board = ({ tiles, selectedTile, currentPlayer }) => {



    return (
        <>
            <div className="boardContainer">
                <div className="tilesContainer">
                    {tiles}
                </div>
            </div>
            <div>{currentPlayer === 'W' ? "White's turn" : "Black's turn"}</div>
        </>
    )
}

Board.propTypes = {
    tiles: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.object)),
    selectedTile: PropTypes.arrayOf(PropTypes.number),
    currentPlayer: PropTypes.string
}

export default Board;