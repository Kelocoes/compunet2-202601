rm -rf dist
npm run build
cp -r WEB-INF/ dist
cd dist
zip -r iaslab#compu2#profe.war *