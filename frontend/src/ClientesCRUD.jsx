import { useEffect, useState } from "react";

export default function ClientesCRUD({ onDataChange }) {
  const [clientes, setClientes] = useState([]);
  const [form, setForm] = useState({ cedula: "", nombres: "", apellidos: "", direccion: "", telefono: "" });
  const [editCedula, setEditCedula] = useState(null);
  const [error, setError] = useState(null);

  const fetchClientes = () => {
    fetch("http://localhost:8080/clientes")
      .then((res) => res.json())
      .then(setClientes)
      .catch(() => setError("Error al cargar clientes"));
  };

  useEffect(() => {
    fetchClientes();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validar que los campos requeridos no sean solo espacios en blanco
    if (
      !form.cedula.trim() ||
      !form.nombres.trim() ||
      !form.apellidos.trim()
    ) {
      setError("No se permiten campos vacíos o solo espacios en blanco en cédula, nombres o apellidos.");
      return;
    }
    const method = editCedula ? "PUT" : "POST";
    const url = editCedula ? `http://localhost:8080/clientes/${editCedula}` : "http://localhost:8080/clientes";
    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then(() => {
        setForm({ cedula: "", nombres: "", apellidos: "", direccion: "", telefono: "" });
        setEditCedula(null);
        setError(null);
        fetchClientes();
        if (onDataChange) onDataChange();
      })
      .catch(() => setError("Error al guardar cliente"));
  };

  const handleEdit = (cliente) => {
    setForm(cliente);
    setEditCedula(cliente.cedula);
  };

  const handleDelete = (cedula) => {
    if (!window.confirm("¿Eliminar cliente?")) return;
    fetch(`http://localhost:8080/clientes/${cedula}`, { method: "DELETE" })
      .then(() => {
        fetchClientes();
        if (onDataChange) onDataChange();
      })
      .catch(() => setError("Error al eliminar cliente"));
  };

  return (
    <div>
      <h2>Clientes</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
        <input name="cedula" placeholder="Cédula" value={form.cedula} onChange={handleChange} required disabled={!!editCedula} />
        <input name="nombres" placeholder="Nombres" value={form.nombres} onChange={handleChange} required />
        <input name="apellidos" placeholder="Apellidos" value={form.apellidos} onChange={handleChange} required />
        <input name="direccion" placeholder="Dirección" value={form.direccion} onChange={handleChange} />
        <input name="telefono" placeholder="Teléfono" value={form.telefono} onChange={handleChange} />
        <button type="submit">{editCedula ? "Actualizar" : "Agregar"}</button>
        {editCedula && <button type="button" onClick={() => { setForm({ cedula: "", nombres: "", apellidos: "", direccion: "", telefono: "" }); setEditCedula(null); }}>Cancelar</button>}
      </form>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Cédula</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map((c) => (
            <tr key={c.cedula}>
              <td>{c.cedula}</td>
              <td>{c.nombres}</td>
              <td>{c.apellidos}</td>
              <td>{c.direccion}</td>
              <td>{c.telefono}</td>
              <td>
                <button onClick={() => handleEdit(c)}>Editar</button>
                <button onClick={() => handleDelete(c.cedula)}>Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
