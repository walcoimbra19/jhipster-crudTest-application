import { element, by, ElementFinder } from 'protractor';

export class VendaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-venda div table .btn-danger'));
  title = element.all(by.css('jhi-venda div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class VendaUpdatePage {
  pageTitle = element(by.id('jhi-venda-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  livroInput = element(by.id('field_livro'));
  dataInput = element(by.id('field_data'));
  totalInput = element(by.id('field_total'));

  clienteSelect = element(by.id('field_cliente'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLivroInput(livro: string): Promise<void> {
    await this.livroInput.sendKeys(livro);
  }

  async getLivroInput(): Promise<string> {
    return await this.livroInput.getAttribute('value');
  }

  async setDataInput(data: string): Promise<void> {
    await this.dataInput.sendKeys(data);
  }

  async getDataInput(): Promise<string> {
    return await this.dataInput.getAttribute('value');
  }

  async setTotalInput(total: string): Promise<void> {
    await this.totalInput.sendKeys(total);
  }

  async getTotalInput(): Promise<string> {
    return await this.totalInput.getAttribute('value');
  }

  async clienteSelectLastOption(): Promise<void> {
    await this.clienteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async clienteSelectOption(option: string): Promise<void> {
    await this.clienteSelect.sendKeys(option);
  }

  getClienteSelect(): ElementFinder {
    return this.clienteSelect;
  }

  async getClienteSelectedOption(): Promise<string> {
    return await this.clienteSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class VendaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-venda-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-venda'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
