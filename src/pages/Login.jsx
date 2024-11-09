import React, { useState } from 'react';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault(); 

        try {
            const res = await fetch("http://localhost:9001/project/login", {
                method: 'POST', 
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }), 
            });

            if (res.ok) {
                const data = await res.json();
                console.log(data);
            } else {
                console.error('Login failed:', res.statusText);
            }
        } catch (error) {
            console.error("API link not working", error);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                required
            />
            <input
                type="password"
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)} 
                required
            />
            <button type='submit'>Submit</button>
        </form>
    );
}

export default Login;