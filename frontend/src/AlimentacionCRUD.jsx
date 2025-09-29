import { useEffect, useState } from "react";

export default function AlimentacionCRUD({ onDataChange }) {
  const [alimentos, setAlimentos] = useState([]);
  const [form, setForm] = useState({ descripcion: "", dosis: "" });
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchAlimentos = () => {
    fetch("http://localhost:8080/alimentacion")
      .then((res) => res.json())
      .then(setAlimentos)
      .catch(() => setError("Error al cargar alimentos"));
  };

  useEffect(() => {
    fetchAlimentos();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validar que los campos requeridos no sean solo espacios en blanco
    if (!form.descripcion.trim() || !form.dosis.trim()) {
      setError("No se permiten campos vacíos o solo espacios en blanco en descripción o dosis.");
      return;
    }
    const method = editId ? "PUT" : "POST";
    const url = editId ? `http://localhost:8080/alimentacion/${editId}` : "http://localhost:8080/alimentacion";
    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then(() => {
        setForm({ descripcion: "", dosis: "" });
        setEditId(null);
        setError(null);
        fetchAlimentos();
        if (onDataChange) onDataChange();
      })
      .catch(() => setError("Error al guardar alimento"));
  };

  const handleEdit = (alimento) => {
    setForm({ descripcion: alimento.descripcion, dosis: alimento.dosis });
    setEditId(alimento.id);
  };

  const handleDelete = (id) => {
    if (!window.confirm("¿Eliminar alimento?")) return;
    fetch(`http://localhost:8080/alimentacion/${id}`, { method: "DELETE" })
      .then(() => {
        fetchAlimentos();
        if (onDataChange) onDataChange();
      })
      .catch(() => setError("Error al eliminar alimento"));
  };

  return (
    <div>
      <h2>Alimentación</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
        <input name="descripcion" placeholder="Descripción" value={form.descripcion} onChange={handleChange} required />
        <input name="dosis" placeholder="Dosis" value={form.dosis} onChange={handleChange} required />
        <button type="submit">{editId ? "Actualizar" : "Agregar"}</button>
        {editId && <button type="button" onClick={() => { setForm({ descripcion: "", dosis: "" }); setEditId(null); }}>Cancelar</button>}
      </form>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th>
            <th>Descripción</th>
            <th>Dosis</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {alimentos.map((a) => (
            <tr key={a.id}>
              <td>{a.id}</td>
              <td>{a.descripcion}</td>
              <td>{a.dosis}</td>
              <td>
                <button onClick={() => handleEdit(a)}>Editar</button>
                <button onClick={() => handleDelete(a.id)}>Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
