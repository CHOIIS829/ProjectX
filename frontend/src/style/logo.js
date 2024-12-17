import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const LogoContainer = styled.div`
    cursor: pointer;
    h1, h2, h3 {
        color: ${props => props.$header};
    }
    span {
        color: ${props => props.$span};
    }
`;

// need to review code on the props
const DynamicHeading = ({ tag: Tag, children, ...props }) => {
    return <Tag {...props}>{children}</Tag>;
};

export const Logo = (props) => {
    const navigate = useNavigate();
    const { header, span, tag } = props;

    return (
        <LogoContainer $header={header} $span={span} onClick={() => navigate("/")}>
            <DynamicHeading tag={tag}>
                project<span>X</span>
            </DynamicHeading>
        </LogoContainer>
    );
};