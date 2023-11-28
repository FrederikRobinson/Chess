export const emailChecks = (email) => {
    const emailRegEx = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    return emailRegEx.test(email);
}
export const loginChecks = (username, password) => {
    return username.length === 0 || password.length === 0
}
export const usernameChecks = (username) => {
    return username.length === 0;
}
export const passwordChecks = (password) => {
    return password.length === 0;
}