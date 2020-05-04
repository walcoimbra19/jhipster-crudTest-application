import { IVenda } from 'app/shared/model/venda.model';

export interface ICliente {
  id?: number;
  razaoSocial?: string;
  email?: string;
  vendas?: IVenda[];
}

export class Cliente implements ICliente {
  constructor(public id?: number, public razaoSocial?: string, public email?: string, public vendas?: IVenda[]) {}
}
