import { convertToUnicodePiece, getTileColour } from "../Utils/boardUtils.jsx";
import PropTypes from "prop-types";

const Tile = ({ xPos, yPos, piece, functions }) => {
    const tileColour = getTileColour(xPos, yPos) + functions.isTileSelected(xPos, yPos);

    return (
        <>
            <div className={`${tileColour} x${xPos} y${yPos} tileContainer`} onClick={() => functions.handleTileSelection(xPos, yPos)}>
                <div className={"tileNumber"}>{xPos}{yPos}</div>
                <div className={"tileContent"} >{convertToUnicodePiece(piece.playerColour, piece.pieceType)}</div>
            </div>
        </>
    )
}

Tile.propTypes = {
    xPos: PropTypes.string.isRequired,
    yPos: PropTypes.number.isRequired,
    piece: PropTypes.arrayOf(
        PropTypes.string
    ).isRequired,
    functions: PropTypes.object
}

export default Tile;