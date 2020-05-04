export interface ILivro {
  id?: number;
  titulo?: string;
  descricao?: string;
  preco?: number;
  autorId?: number;
}

export class Livro implements ILivro {
  constructor(public id?: number, public titulo?: string, public descricao?: string, public preco?: number, public autorId?: number) {}
}
