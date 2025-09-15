import { useEffect, useState } from "react";

export default function ReporteClientesPorcinos() {
  const [reporte, setReporte] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/reporte-clientes-porcinos")
      .then((res) => {
        if (!res.ok) throw new Error("Error al obtener el reporte");
        return res.json();
      })
      .then((data) => {
        setReporte(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Cargando reporte...</p>;
  if (error) return <p>Error: {error}</p>;

  const handleDescargarPDF = () => {
    window.open("http://localhost:8080/reporte-clientes-porcinos/pdf", "_blank");
  };

  return (
    <div>
      <h2>Reporte de Clientes y Porcinos</h2>
      <button onClick={handleDescargarPDF} style={{ marginBottom: 10 }}>Descargar PDF</button>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Cédula</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Identificación Porcino</th>
            <th>Raza</th>
            <th>Edad</th>
            <th>Peso</th>
          </tr>
        </thead>
        <tbody>
          {reporte.map((item, idx) => (
            <tr key={idx}>
              <td>{item.clienteCedula}</td>
              <td>{item.clienteNombres}</td>
              <td>{item.clienteApellidos}</td>
              <td>{item.porcinoIdentificacion}</td>
              <td>{item.raza}</td>
              <td>{item.edad}</td>
              <td>{item.peso}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
