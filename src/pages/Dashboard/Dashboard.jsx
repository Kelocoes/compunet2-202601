import { Box, Typography } from "@mui/material";

export default function Dashboard() {
    return (
        <Box style={{ height: "100vh", display: "flex", alignItems: "center", flexDirection: "column" }}>
            <Typography variant="h1" style={{ fontSize: "2rem" }}>Estoy en el dashboard!</Typography>
        </Box>
    );
}