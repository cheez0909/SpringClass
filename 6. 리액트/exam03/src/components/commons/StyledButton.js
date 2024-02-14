import styled, { css } from 'styled-components';
import { buttonSizes, fontSizes } from '../styles/size';
import color from '../../components/styles/color';

const { small, medium, big } = buttonSizes;
const { small: fSmall, medium: fMedium, big: fBig } = fontSizes;

const commonStyle = css`
  border: 0;
  cursor: pointer;
  border-radius: 5px;
`;

export const SmallButton = styled.button`
  width: ${small.width}px;
  height: ${small.height}px;
  font-size: ${fSmall}rem;
  background: ${({ bColor }) => color[bColor] || bColor};
  color: ${({ color }) => color[color] || color};
  ${commonStyle}
  ${({ active }) =>
    active &&
    css`
      border: 2px solid #000;
    `}
`;

export const MediumButton = styled.button`
  width: ${medium.width}px;
  height: ${medium.height}px;
  font-size: ${fMedium}rem;
  background: ${({ bColor }) => color[bColor] || bColor};
  color: ${({ color }) => color[color] || color};
  ${commonStyle}
  ${({ active }) =>
    active &&
    css`
      border: 2px solid #000;
    `}
`;

export const BigButton = styled.button`
  width: ${big.width}px;
  height: ${big.height}px;
  font-size: ${fBig}rem;
  background: ${({ bColor }) => color[bColor] || bColor};
  color: ${({ color }) => color[color] || color};
  ${commonStyle}
  ${({ active }) =>
    active &&
    css`
      border: 2px solid #000;
    `}
`;
