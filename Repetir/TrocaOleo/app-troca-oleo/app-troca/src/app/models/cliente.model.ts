export interface Cliente {
  //interface porque no Angular o Model serve apenas para tipar os dados do JSON que vêm da API, sem precisar de métodos.
  // A interface garante a segurança do TypeScript e é removida na compilação, deixando o JS final mais leve.

  id?: number;
  name: string;
  telefone: string;
  endereco?: string;
  cpf?: string;

}
