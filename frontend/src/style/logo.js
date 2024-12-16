import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const LogoContainer = styled.div`
    cursor: pointer;
    h2{
        color: ${props => props.$header};
    };
    span{
        color: ${props => props.$span};
    }
`;

export const Logo = (props) => {

    const navigate = useNavigate();

    return(
        <LogoContainer $header={props.header} $span={props.span} onClick={() => navigate("/")}>
            <h2>project<span>X</span></h2>
        </LogoContainer>
    );
};