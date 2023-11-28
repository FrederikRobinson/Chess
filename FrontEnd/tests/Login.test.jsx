import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Login from "../src/Components/Login.jsx";

describe('Login form tests', () => {

    const user = { username: "TestUser1", password: "password123" }

    afterEach(() => {
        vi.clearAllMocks();
        vi.resetAllMocks();
    })
    describe('username tests', () => {

        it('should show invalid username message when username input is dirty and invalid', async () => {

            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const usernameInput = screen.getByPlaceholderText(`Username`);
            const passwordInput = screen.getByPlaceholderText(`Password`);

            await userEvent.click(usernameInput);
            await userEvent.click(passwordInput);

            expect(screen.getByText(`Please enter a username`)).toBeInTheDocument();
        });

        it('should not show invalid username message when username input is valid', async () => {

            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const passwordInput = screen.getByPlaceholderText(`Password`);
            const usernameInput = screen.getByPlaceholderText(`Username`);

            await userEvent.type(usernameInput, user.username);

            await userEvent.click(passwordInput);

            expect(screen.queryByText(`Please enter a username`)).not.toBeInTheDocument();
        });
    });
    describe('password tests', () => {

        it('should show invalid password message when password input is dirty and invalid', async () => {

            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const usernameInput = screen.getByPlaceholderText(`Username`);
            const passwordInput = screen.getByPlaceholderText(`Password`);


            await userEvent.click(passwordInput);
            await userEvent.click(usernameInput);
            expect(screen.getByText(`Please enter a password`)).toBeInTheDocument();
        });

        it('should not show invalid password message when password input is valid', async () => {

            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const passwordInput = screen.getByPlaceholderText(`Password`);
            const usernameInput = screen.getByPlaceholderText(`Username`);

            await userEvent.type(passwordInput, user.password);

            await userEvent.click(usernameInput);

            expect(screen.queryByText(`Please enter a password`)).not.toBeInTheDocument();
        });
    });
    describe('Login button tests', () => {

        it('should have a disabled login button before any inputs are made', async () => {
            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const loginButton = screen.getByDisplayValue(/Submit/i);

            expect(loginButton).toBeDisabled();
        });

        it('should call loginHandler when form is valid and login button clicked', async () => {
            const mockLoginHandler = vi.fn((username, password) => { });
            render(<Login loginHandler={mockLoginHandler} />);

            const loginButton = screen.getByDisplayValue(/Submit/i);
            const passwordInput = screen.getByPlaceholderText("Password");
            const usernameInput = screen.getByPlaceholderText("Username");


            await userEvent.click(passwordInput);
            await userEvent.type(passwordInput, user.password);
            await userEvent.click(usernameInput);
            await userEvent.type(usernameInput, user.username);
            expect(loginButton).not.toBeDisabled();

            await userEvent.click(loginButton);
            expect(mockLoginHandler).toHaveBeenCalled();
        });
    });
});