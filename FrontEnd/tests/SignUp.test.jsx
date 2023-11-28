import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SignUp from "../src/Components/SignUp.jsx";

describe('SignUp form tests', () => {

    const badEmail = `not.valid`;
    const goodUser = { username: "Alice", password: "password123", email: "test@email.com" }

    afterEach(() => {
        vi.clearAllMocks();
        vi.resetAllMocks();
    })

    describe('email tests', () => {

        it('should show invalid email message when email input is dirty and invalid', async () => {
            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const emailInput = screen.getByPlaceholderText(`Email`);
            const passwordInput = screen.getByPlaceholderText(`Password`);


            await userEvent.type(emailInput, badEmail);

            await userEvent.click(passwordInput);

            expect(screen.getByText(`Invalid email`)).toBeInTheDocument();
        });

        it('should not show invalid email message when email input is valid', async () => {

            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const passwordInput = screen.getByPlaceholderText(`Password`);
            const emailInput = screen.getByPlaceholderText(`Email`);

            await userEvent.type(emailInput, goodUser.email);

            await userEvent.click(passwordInput);

            expect(screen.queryByText(`Invalid email`)).not.toBeInTheDocument();
        });
    });
    describe('username tests', () => {

        it('should show invalid username message when username input is dirty and invalid', async () => {

            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const usernameInput = screen.getByPlaceholderText(`Username`);
            const passwordInput = screen.getByPlaceholderText(`Password`);

            await userEvent.click(usernameInput);
            await userEvent.click(passwordInput);

            expect(screen.getByText(`Please enter a username`)).toBeInTheDocument();
        });

        it('should not show invalid username message when username input is valid', async () => {

            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const passwordInput = screen.getByPlaceholderText(`Password`);
            const usernameInput = screen.getByPlaceholderText(`Username`);

            await userEvent.type(usernameInput, goodUser.username);

            await userEvent.click(passwordInput);

            expect(screen.queryByText(`Please enter a username`)).not.toBeInTheDocument();
        });
    });
    describe('password tests', () => {

        it('should show invalid password message when password input is dirty and invalid', async () => {

            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const usernameInput = screen.getByPlaceholderText(`Username`);
            const passwordInput = screen.getByPlaceholderText(`Password`);

            
            await userEvent.click(passwordInput);
            await userEvent.click(usernameInput);
            expect(screen.getByText(`Please enter a password`)).toBeInTheDocument();
        });

        it('should not show invalid password message when password input is valid', async () => {

            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const passwordInput = screen.getByPlaceholderText(`Password`);
            const usernameInput = screen.getByPlaceholderText(`Username`);

            await userEvent.type(passwordInput, goodUser.password);

            await userEvent.click(usernameInput);

            expect(screen.queryByText(`Please enter a password`)).not.toBeInTheDocument();
        });
    });
    describe('SignUp button tests', () => {

        it('should have a disabled signUp button before any inputs are made', () => {
            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const signUpButton = screen.getByDisplayValue(/Submit/i);

            expect(signUpButton).toBeDisabled();
        });

        it('should call signUpHandler when form is valid and signUp button clicked', async () => {
            const mockSignUpHandler = vi.fn((username, password) => { });
            render(<SignUp signUpHandler={mockSignUpHandler} />);

            const signUpButton = screen.getByDisplayValue(/Submit/i);
            const passwordInput = screen.getByPlaceholderText("Password");
            const emailInput = screen.getByPlaceholderText("Email");
            const usernameInput = screen.getByPlaceholderText("Username");


            await userEvent.click(emailInput);
            await userEvent.type(emailInput, goodUser.email);
            await userEvent.click(passwordInput);
            await userEvent.type(passwordInput, goodUser.password);
            await userEvent.click(usernameInput);
            await userEvent.type(usernameInput, goodUser.username);
            expect(signUpButton).not.toBeDisabled();

            await userEvent.click(signUpButton);
            expect(mockSignUpHandler).toHaveBeenCalled();
        });
    });
});