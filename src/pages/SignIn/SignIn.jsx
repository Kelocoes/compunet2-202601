import { useRef } from 'react'
import {
    Box,
    Button,
    Container,
    Paper,
    Stack,
    TextField,
    Typography,
} from '@mui/material'
import authService from '../../services/auth/auth.service'
import { useNavigate } from 'react-router'

function SignIn() {
    const formRef = useRef(null)
    const nav = useNavigate()

    const handleSubmit = async (event) => {
        event.preventDefault()
        const formData = new FormData(formRef.current)
        const username = formData.get('username') ?? ''
        const password = formData.get('password') ?? ''

        const result = await authService.login({ username, password })
        if (result.error) {
            alert('Login failed: ' + result.message)
        } else {
            alert('Login successful!')
            localStorage.setItem('token', result.data.accessToken)
            nav('/dashboard') // Redirigir al usuario a la página de dashboard
        }
    }

    return (
        <Box sx={{ minHeight: '100vh', display: 'grid', placeItems: 'center', px: 2 }}>
            <Container maxWidth="sm">
                <Paper elevation={3} sx={{ p: { xs: 3, md: 4 } }}>
                    <Stack spacing={3} component="form" ref={formRef} onSubmit={handleSubmit}>
                        <Box>
                            <Typography variant="h4" component="h1" fontWeight={700}>
                                Login
                            </Typography>
                        </Box>

                        <TextField
                            label="Username"
                            name="username"
                            fullWidth
                        />

                        <TextField
                            label="Password"
                            name="password"
                            type="password"
                            fullWidth
                        />

                        <Button type="submit" variant="contained">
                            Entrar
                        </Button>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    )
}

export default SignIn

