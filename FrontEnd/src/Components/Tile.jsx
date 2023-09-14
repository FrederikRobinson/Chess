import { getTileColour } from "../Utils/boardUtils.jsx";

const Tile = ({ xPos, yPos }) => {
    const tileColour = getTileColour(xPos, yPos);

    return (
        <>
            <div className={`${tileColour} x${xPos} y${yPos} tileContainer`}>
                <div className={"tileContent"}>{xPos}{yPos}</div>
            </div>
        </>
    )
}

export default Tile;