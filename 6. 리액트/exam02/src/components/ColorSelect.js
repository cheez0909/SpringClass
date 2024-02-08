const colors = ['red', 'blue', 'green', 'orange', 'pink', 'black'];

const ColorSelect = ({ setColor }) => {
  const onClick = (color) => {
    
    setColor((preState) => {
      console.log(preState);
      return color;
    });
    console.log(color);
  };

  return (
    <>
      <div>
        {colors.map((color) => (
          <button type="button" onClick={() => onClick(color)} key={color}>
            {color}
          </button>
        ))}
      </div>
    </>
  );
};

export default ColorSelect;
