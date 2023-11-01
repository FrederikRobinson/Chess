import Tile from '../Components/Tile.jsx'

export const createBoard = (board, functions) => {
    let tiles = [];
    for (let y = 1; y <= 8; y++) {
        tiles = [...tiles, createRow(y, board, functions)]
    }
    return tiles;
}

const createRow = (y, board, functions) => {
    let row = [];
    for (let x = 1; x <= 8; x++) {
        row = [...row, createTile(x, y, board[x - 1][y - 1], functions)]
    }
    return row;
}

const createTile = (x, y, piece, functions) => {
    const yPos = y;
    const xPos = posToLetter(x)
    return <Tile xPos={xPos} yPos={yPos} piece={piece} functions={functions} key={`${xPos} ${yPos}`} />;
}

export const getTileColour = (xPos, yPos) => {
    const x = letterToPos(xPos);
    const y = yPos;
    return POS_TO_COLOUR[(x + y) % 2];
}

const POS_TO_COLOUR = ['black', 'white'];

const POS_TO_LETTER = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];

const posToLetter = (pos) => {
    return POS_TO_LETTER[pos - 1];
}

const letterToPos = (letter) => {
    return POS_TO_LETTER.indexOf(letter) + 1;
}

export const emptyBoard = [
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']]
];
export const notEmptyBoard = [
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['P', 'B'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']],
    [['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X'], ['X', 'X']]
];

export const nextPlayer = (player) => {
    if (player == 'W') {
        return 'B';
    }
    return 'W';
};
export const convertToUnicodePiece = (pieceColour, pieceType) => {
    if (pieceColour == 'X' || pieceType == 'X') {
        return "";
    }
    let letterCode = 4 + (pieceColour == 'B' ? 6 : 0);
    switch (pieceType) {
        case 'P': letterCode += 1;
        case 'N': letterCode += 1;
        case 'B': letterCode += 1;
        case 'R': letterCode += 1;
        case 'Q': letterCode += 1;
    }
    return String.fromCharCode(parseInt("265" + letterCode.toString(16), 16))
}