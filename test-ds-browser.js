const puppeteer = require('puppeteer');

(async () => {
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto('http://10.0.2.228:3001', { waitUntil: 'networkidle0' });
  await page.waitForSelector('ul li', { timeout: 5000 }).catch(() => {});
  const content = await page.content();
  console.log(content);
  await browser.close();
})();

//node test-ds-browser.js