# ==========================================
# Etapa 1: Desarrollo con Hot Reload (Dev)
# ==========================================
FROM node:20-alpine AS dev
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .

EXPOSE 4200

# Ejecuta Angular Dev Server escuchando cambios en tiempo real (Hot Reload)
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--poll", "1000"]

# ==========================================
# Etapa 2: Construcción (Build)
# ==========================================
FROM node:20-alpine AS build
WORKDIR /app

# Copiar archivos de dependencias e instalarlas
COPY package*.json ./
RUN npm ci

# Copiar el código fuente y compilar la aplicación Angular
COPY . .
RUN npm run build

# ==========================================
# Etapa 3: Servidor Web Producción (Nginx)
# ==========================================
FROM nginx:alpine AS prod

# Copiar configuración personalizada de Nginx para manejo de rutas SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copiar los archivos compilados desde la etapa de construcción
COPY --from=build /app/dist/english-skills-tracker/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
