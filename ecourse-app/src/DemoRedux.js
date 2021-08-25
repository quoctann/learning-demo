export default function DemoRedux(props) {
    const { value, helloVi, helloEn } = props;
    return (
        <>
            <h1>{value}</h1>
            <input type="button" onClick={helloVi} value="Vietnamese"/>
            <input type="button" onClick={helloEn} value="English"/>
        </>
    )
}