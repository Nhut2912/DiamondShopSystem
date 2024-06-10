
export default function ConvertLocalDateToFormat(date) {
    const parsedDate = new Date(Date.parse(date));
    const formattedDate = parsedDate.toLocaleString('en-US', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    });
    return formattedDate;
}