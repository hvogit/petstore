// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  API_DOMAIN: 'localhost:8080',
  API_ENDPOINT: 'http://localhost:8080/api',
  AUTH_ENDPOINT: 'http://localhost:7070/oauth/token',
  ACCESS_TOKEN: 'access_token',
  REFRESH_TOKEN: 'refresh_token'
};
