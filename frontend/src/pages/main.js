import styled from "styled-components";

const Heading = styled.h1`
    color: var(--main-color);
    font-weight: bold;
    text-align: center;
`;

export const Main = () => {
    return(
        <>
            <Heading>This is the main page</Heading>
        </>
    );
};