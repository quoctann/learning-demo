// export const reducer = (state="Unknow", action) => {
//     switch (action.type) {
//         case 'vi':
//             return 'Xin chào';
//         case 'en':
//             return 'Hello';
//         default:
//             return false;
//     }
// }

export const reducer = (state=null, action) => {
    if (action.type === 'login')
        return action.payload // Chứa thông tin user
    return state;
}