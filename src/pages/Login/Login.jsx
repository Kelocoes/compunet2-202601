import {
    Box,
    Button,
    Container,
    Paper,
    Stack,
    TextField,
    Typography,
} from '@mui/material';

function SignIn() {

    return (
        <Box sx={{ minHeight: '100vh', display: 'grid', placeItems: 'center', px: 2 }}>
            <Container maxWidth="sm">
                <Paper elevation={3} sx={{ p: { xs: 3, md: 4 } }}>
                    <Stack spacing={3} component="form">
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
    );
}

export default SignIn;
